1. film tablosunda bulunan filmleri rating değerlerine göre gruplayınız.

"R"
"NC-17"
"G"
"PG"
"PG-13"
```sql
Select rating from film
group by rating;
```

2. film tablosunda bulunan filmleri replacement_cost sütununa göre grupladığımızda film sayısı 50 den fazla olan 
replacement_cost değerini ve karşılık gelen film sayısını sıralayınız.

13.99	55

20.99	57

27.99	53

29.99	53

12.99	55

14.99	51

22.99	55

21.99	55
```sql
select replacement_cost, count(replacement_cost) from film
group by replacement_cost
having count(*)>50;
```

3. customer tablosunda bulunan store_id değerlerine karşılık gelen müşteri sayılarını nelerdir?

1	326

2	273
```sql
select store_id, count(store_id) from customer
group by store_id; 
```

4. city tablosunda bulunan şehir verilerini country_id sütununa göre gruplandırdıktan sonra en fazla 
şehir sayısı barındıran country_id bilgisini ve şehir sayısını paylaşınız.

44	60
```sql
select country_id, count(city) as numbers from city
group by country_id
order by numbers desc
limit 1;
```
