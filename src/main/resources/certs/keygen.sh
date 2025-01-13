#!/bin/bash

rm *.pem

#Generate a keypair
openssl genrsa -out keypair.pem 2048

#Extract the public key
openssl rsa -in keypair.pem -pubout -out public.pem

#Extract and convert the private key to PKCS8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

rm keypair.pem