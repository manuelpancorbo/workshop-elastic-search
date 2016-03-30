#!/usr/bin/env bash

curl -XPUT "http://ws-elastic.dm:9200/ads/ad/5" -d'
{
    "id": 5,
    "title": "Gonna be deleted",
    "body": "...",
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}'

curl -XDELETE "http://ws-elastic.dm:9200/ads/ad/5"
