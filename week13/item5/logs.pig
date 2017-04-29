records = LOAD '/user/vagrant/tempdata/all.log' using PigStorage(' ')
  AS (date:chararray, time:chararray,ip:chararray,method:chararray,url:chararray,query:chararray,port:int,username:chararray,c_ip:chararray,cs:chararray,status:int,substatus:int,w_status:int,t_time:int);
filtered_records = FILTER records BY status == 200 AND NOT url matches '*index.*';

X = FOREACH filtered_records GENERATE date,url;

A = GROUP X BY (date,url);

B = FOREACH A GENERATE group, COUNT(X) as num;

C = GROUP B BY group.date;

D = FOREACH C {
    SA = ORDER B BY num DESC;
    SB = LIMIT SA 1;
    GENERATE FLATTEN(SB.group);
}

DUMP D;
