#!/bin/bash

curl -X PUT "localhost:9200/logstash-*" -H 'Content-Type: application/json' -d'
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 0
  },
  "mappings": {
    "properties": {
      "@timestamp": {
        "type": "date"
      },
      "title": {
        "type": "text"
      },
      "description": {
        "type": "text"
      }
    }
  }
}
'
