SELECT B.dat,url FROM (SELECT s.dat,s.url,count(s.url) AS url_count2
FROM Webserver_logs s
WHERE status = '200'
AND url NOT LIKE '%index.%'
GROUP BY s.dat,s.url)
as B
INNER JOIN 
(SELECT a.dat,max(url_count) AS url_max FROM(SELECT dat,url,count(url) AS url_count
FROM Webserver_logs
WHERE status = '200'
AND url NOT LIKE '%index.%'
GROUP BY dat,url) a
GROUP BY a.dat)
as C
ON B.dat = C.dat
AND B.url_count2 = C.url_max;


