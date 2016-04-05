#!/usr/bin/env bash

# Location as string
curl -XPUT "http://ws-elastic.dm:9200/ads/ad/1" -d'
{
    "id": 1,
    "title": "Beautiful thing",
    "body": "It'\''s a lie, it'\''s ugly thing.",
    "price": 20.50,
    "location": "41.3783325,2.1686425",
    "zipCode": "08001"
}'

# Location as array long/lat
curl -XPUT "http://ws-elastic.dm:9200/ads/ad/2" -d'
{
    "id": 2,
    "title": "Another beautiful thing",
    "body": "worst thing ever.",
    "price": 15,
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}'

# Document with auto-generated uuid
curl -XPOST "http://ws-elastic.dm:9200/ads/ad/" -d'
{
    "id": 50,
    "title": "Random id document",
    "body": "....",
    "price": 100,
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}'

# Will fail due to location
curl -XPUT "http://ws-elastic.dm:9200/ads/ad/3" -d'
{
    "id": 3,
    "title": "Another beautiful thing",
    "body": "worst thing ever.",
    "price": 200,
    "location": "NotALocation",
    "zipCode": "08001"
}'

# Will fail due to already exists document
curl -XPOST "http://ws-elastic.dm:9200/ads/ad/2/_create" -d'
{
    "id": 2,
    "title": "Another beautiful thing",
    "body": "worst thing ever.",
    "price": 200,
    "location": [2.1660139, 41.3791979],
    "zipCode": "08001"
}'
