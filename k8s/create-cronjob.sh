#!/bin/bash
set -e

kubectl create configmap ${DEPLOYMENT_NAME} --from-env-file=k8s/${DEPLOYMENT_NAME}.properties --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cm.yaml
# https://crontab.guru/every-day-8am -> rather at 1am
kubectl create cronjob ${DEPLOYMENT_NAME} --image=${DOCKER_IMG} --schedule="0 1 * * *" --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cronjob.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cronjob.yaml
kubectl set env cronjob/${DEPLOYMENT_NAME} --from configmap/${DEPLOYMENT_NAME} --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-cronjob-cm.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-cronjob-cm.yaml

# on demand only plz
#kubectl create job --from=cronjob/ms-covid-data-loader ms-convid-data-loader-on-demand --dry-run=client -o yaml \
#  > ${DEPLOYMENT_NAME}-job.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-job.yaml