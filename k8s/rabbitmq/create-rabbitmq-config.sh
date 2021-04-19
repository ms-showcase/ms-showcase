#!/bin/bash
set -e

kubectl create configmap rabbitmq-config --from-file k8s/rabbitmq/definitions.json --from-file k8s/rabbitmq/rabbitmq.conf
kubectl patch deployment ${DEPLOYMENT_NAME} --patch k8s/rabbitmq/patch-rabbitmq-volume.yml
