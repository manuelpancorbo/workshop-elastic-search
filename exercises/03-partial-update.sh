#!/usr/bin/env bash

curl -XPOST "http://localhost:9200/ads/ad/2/_update" -d '
{
    "doc": {
        "body" : "meh, not bad"
    }
}
' | python -m json.tool