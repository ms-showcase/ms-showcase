#!/bin/bash
set -e

kubectl create configmap ${DEPLOYMENT_NAME} --from-file=k8s/${DEPLOYMENT_NAME}.properties --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cm.yaml
# https://crontab.guru/every-day-8am
kubectl create cronjob ${DEPLOYMENT_NAME} --image=${DOCKER_IMG} --schedule="0 8 * * *" --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cronjob.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cronjob.yaml
kubectl set env cronjob/${DEPLOYMENT_NAME} --from configmap/${DEPLOYMENT_NAME} --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cronjob-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cronjob-cm.yaml