records = LOAD '/user/vagrant/tempdata/al.log' using PigStorage(' ')
  AS (date:chararray, time:chararray,ip:chararray,method:chararray,url:chararray,query:chararray,port:int,username:chararray,c_ip:chararray,cs:chararray,status:int,substatus:int,w_status:int,t_time:int);
filtered_records = FILTER records BY status == 200 AND NOT url matches '*index.*';

X = FOREACH filtered_records GENERATE SUBSTRING (date, 0, 7) as month,url;

A = GROUP X BY (month,url);

B = FOREACH A GENERATE group, COUNT(X) as num;

C = GROUP B BY group.month;

D = FOREACH C {
    SA = ORDER B BY num DESC;
    SB = LIMIT SA 1;
    GENERATE FLATTEN(SB.group);
}

DUMP D;