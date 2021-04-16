#!/bin/bash
set -e

kubectl create configmap ${DEPLOYMENT_NAME} --from-env-file=k8s/${DEPLOYMENT_NAME}.properties --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cm.yaml

kubectl create deployment ${DEPLOYMENT_NAME} --image=${DOCKER_IMG} --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-deploy.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-deploy.yaml

kubectl set env deployment/${DEPLOYMENT_NAME} --from configmap/${DEPLOYMENT_NAME} --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-deploy-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-deploy-cm.yaml

if [ "${SVC_TYPE}" == "nodeport" ]; then
  kubectl create service $SVC_TYPE ${DEPLOYMENT_NAME} --node-port=$SVC_NODEPORT --tcp=$SVC_PORT:$SVC_PORT --dry-run=client -o yaml \
    > ${DEPLOYMENT_NAME}-svc.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-svc.yaml
else
  kubectl create service $SVC_TYPE ${DEPLOYMENT_NAME} --tcp=$SVC_PORT:$SVC_PORT --dry-run=client -o yaml \
    > ${DEPLOYMENT_NAME}-svc.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-svc.yaml
fi

if [ ! -z "${LIVENESS_PROBE}" ]; then
#  cat k8s/patch-livenessprobe.yml | k8s/mo
  cat k8s/patch-livenessprobe.yml | k8s/mo | kubectl patch deployment ${DEPLOYMENT_NAME} --patch "$(cat)"
fi

if [ ! -z "${READINESS_PROBE}" ]; then
#  cat k8s/patch-readinessprobe.yml | k8s/mo
  cat k8s/patch-readinessprobe.yml | k8s/mo | kubectl patch deployment ${DEPLOYMENT_NAME} --patch "$(cat)"
fi
