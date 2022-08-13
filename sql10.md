/*
1- city tablosu ile country tablosunda bulunan şehir (city) ve ülke (country) isimlerini birlikte görebileceğimiz
LEFT JOIN sorgusunu yazınız.
*/
select city.city, country.country
from city
LEFT JOIN country
ON city.country_id = country.country_id;

/*
2- customer tablosu ile payment tablosunda bulunan payment_id ile customer tablosundaki first_name ve last_name
isimlerini birlikte görebileceğimiz RIGHT JOIN sorgusunu yazınız.
*/
select payment_id, first_name, last_name
from payment
RIGHT JOIN customer
ON payment.customer_id = customer.customer_id;

/*
3- customer tablosu ile rental tablosunda bulunan rental_id ile customer tablosundaki first_name ve last_name
isimlerini birlikte görebileceğimiz FULL JOIN sorgusunu yazınız.
*/
select rental_id, first_name, last_name
from rental
FULL JOIN customer
ON rental.customer_id = customer.customer_id;
