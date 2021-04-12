#!/bin/sh

kubectl create configmap ${DEPLOYMENT_NAME} --from-env-file=k8s/${DEPLOYMENT_NAME}.properties --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cm.yaml
kubectl create deployment ${DEPLOYMENT_NAME} --image=${DOCKER_IMG} --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-deploy.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-deploy.yaml
kubectl set env deployment/${DEPLOYMENT_NAME} --from configmap/${DEPLOYMENT_NAME} --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-deploy-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-deploy-cm.yaml
kubectl create service $SVC_TYPE ${DEPLOYMENT_NAME} --tcp=$SVC_PORT:$SVC_PORT --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-svc.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-svc.yaml