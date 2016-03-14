#!/usr/bin/env bash

# Location as array long/lat
curl -XPUT "http://default.dm:9200/ads/ad/99" -d'
{
    "id": 99,
    "title": "Malformed doc",
    "body": "Double brace!.",
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}}}}}}}}
'

# Retrieves the malformed JSON document, WTF?!
curl -XGET "http://default.dm:9200/ads/ad/99"