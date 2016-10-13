#!/usr/bin/env bash

curl -XPUT "http://localhost:9200/ads/ad/5" -d'
{
    "id": 5,
    "title": "Gonna be deleted",
    "body": "...",
    "price": 1000,
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}' | python -m json.tool

curl -XDELETE "http://localhost:9200/ads/ad/5" | python -m json.tool
