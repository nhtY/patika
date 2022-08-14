/*
1- film tablosunda film uzunluğu length sütununda gösterilmektedir. Uzunluğu ortalama film uzunluğundan 
fazla kaç tane film vardır? = 489
*/
select count(*) from film
where film.length > (
	Select avg(film.length) from film);
/*
2- film tablosunda en yüksek rental_rate değerine sahip kaç tane film vardır? = 336
*/
select count(*) from film
where film.rental_rate >= ALL(
	Select rental_rate from film);

/*
3- film tablosunda en düşük rental_rate ve en düşün replacement_cost değerlerine sahip filmleri sıralayınız.
*/
select * from film
where rental_rate <= All(select rental_rate from film)
AND replacement_cost <= All(select replacement_cost from film);

/*
4- payment tablosunda en fazla sayıda alışveriş yapan müşterileri(customer) sıralayınız.
*/

/* sıralı*/
select topFive.numberOFshop, * from customer
RIGHT JOIN (select count(*) as numberOFshop, customer_id from payment
group by customer_id order by count(*) desc limit 5) as topFive
ON customer.customer_id = topFive.customer_id
ORDER BY topFive.numberOFshop desc;

/* sırasız */
SELECT * FROM customer
JOIN payment On customer.customer_id = ANY
(SELECT customer_id FROM payment
GROUP BY customer_id 
ORDER BY COUNT(customer_id) DESC
LIMIT 5)
LIMIT 5;
