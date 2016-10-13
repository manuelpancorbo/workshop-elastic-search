#!/usr/bin/env bash

#Fixed in 2.x versions ;)

# Location as array long/lat
curl -XPUT "http://localhost:9200/ads/ad/99" -d'
{
    "id": 99,
    "title": "Malformed doc",
    "body": "Double brace!.",
    "price": 500,
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}}}}}}}}
'

# Retrieves the malformed JSON document, WTF?!
curl -XGET "http://localhost:9200/ads/ad/99"