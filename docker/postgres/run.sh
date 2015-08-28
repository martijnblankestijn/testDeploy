#! /usr/bin/env bash
thisDir=`dirname $0`

docker run -d -p 5432:5432 --cidfile=${thisDir}/CONTID --name postgres havenbedrijfr/postgres
