#! /bin/bash

for i in {0..5}
do
    nohup ./redis-server redis-700$i.conf &
done