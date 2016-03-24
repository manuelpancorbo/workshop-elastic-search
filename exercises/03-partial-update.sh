#!/usr/bin/env bash

curl -XPOST "http://ws-elastic.dm:9200/ads/ad/2/_update" -d '
{
    "doc": {
        "body" : "meh, not bad"
    }
}
'