#!/usr/bin/env bash

curl -XPUT "http://ws-elastic.dm:9200/ads/ad/5" -d' | python -m json.tool
{
    "id": 5,
    "title": "Gonna be deleted",
    "body": "...",
    "price": 1000,
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}'

curl -XDELETE "http://ws-elastic.dm:9200/ads/ad/5" | python -m json.tool
