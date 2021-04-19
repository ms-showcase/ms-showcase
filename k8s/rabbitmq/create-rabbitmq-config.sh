#!/bin/bash
set -e

kubectl create configmap rabbitmq-config --from-file k8s/rabbitmq/definitions.json --from-file k8s/rabbitmq/rabbitmq.conf --dry-run=client -o yaml \
  > ${DEPLOYMENT_NAME}-rabbitmq-config.yaml && kubectl apply -f ${DEPLOYMENT_NAME}-rabbitmq-config.yaml
cat k8s/rabbitmq/patch-rabbitmq-volume.yml | k8s/mo | kubectl patch deployment ${DEPLOYMENT_NAME} --patch "$(cat)"