#! /usr/bin/env bash
thisDir=`dirname $0`
docker build -t havenbedrijfr/postgres ${thisDir}
