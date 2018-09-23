#!/bin/bash

# SSH to appserver and execute redeploy of the app
ssh -i vanhacks-babies.pem ubuntu@50.112.155.44 ./deploy.sh &
