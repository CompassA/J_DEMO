#! /bin/bash

for i in {0..5}
do
    cp /etc/redis.conf redis-700$i.conf
done
