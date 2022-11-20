# PatikaStore



Patika ekibi elektronik eşyaların satıldığı bir sanal bir mağaza açmaya karar verdi ve bu mağazanın ürün yönetim sistemini siz patika gönüllülerinden yapmasını ekliyor.



- Sanal Mağazanın adı "PatikaStore" olacaktır.



- Mağazada Markalar oluşturulacak ve ürünler bu markalar ile eşleşecektir.



- id : Markanın sistemde kayıtlı benzersiz numarası



- name : Markanın adı



- Markalar listelenirken her zaman alfabe sırasıyla listelenmelidir.



- Markalar statik olarak kod blokları içerisinden aşağıdaki sıra ile eklenmelidir.



- Markalar : Samsung, Lenovo, Apple, Huawei, Casper, Asus, HP, Xiaomi, Monster



- Mağazada şuan için 2 tür ürün grubu satılması planlanmaktadır : Cep Telefonları, Notebook



- Daha sonrasında farklı ürün gruplarını eklenebilir olmalıdır.



- Cep Telefonu ürünlerinin özellikleri :



- Ürünün sistemde kayıtlı benzersiz numarası



- Birim fiyatı



- İndirim oranı



- Stok miktarı



- Ürün adı



- Marka bilgisi (Sistemde ekli olan markalar kullanılacaktır)



- Telefonun hafıza bilgisi (128 GB, 64 GB)



- Ekran Boyutu (6.1 Inc)



- Pil Gücü (4000)



- RAM (6 MB)



- Renk (Siyah,Kırmızı,Mavi)



- Notebook ürünlerinin özellikleri :



- Ürünün sistemde kayıtlı benzersiz numarası



- Birim fiyatı



- İndirim oranı



- Stok miktarı



- Ürün adı



- Marka bilgisi (Sistemde ekli olan markalar kullanılacaktır)



- Ram (8 GB)



- Depolama (512 SSD)



- Ekran Boyutu (14 inç)



- Kullanıcı sistem üzerinden ilgili kategorideki (Notebook, Cep Telefonları vb.) ürünleri listeyebilimeli



- Ürünler listelenirken tablo şeklinde konsol ekranında gösterilmeli (System.out.format() kullanılabilir).



- Kullanıcı ürün ekleyebilmeli ve ürünün grubunu (Cep Telefonu, Notebook vb.) seçebilmeli.



- Kullanıcı ürünleri benzersiz numaralarına göre silebilmeli.



- Kullanıcı ürünlerin benzersiz numaralarına ve markalarına göre filtreleyip listeleyebilmeli.



## Örnek Çıktılar



```text

<-- Patika | Product Management Panel -->
1- Notebook Operations
2- Mobile phone Operations
3- List the Brands
0- Exit
Enter your choice: 2
```


```text
---- Mobile Phone ----

1- List All Mobile Phones
2- Filter by id
3- Filter by brand
4- Delete by ID
5- Add product
0- Go back
Enter your choice: 1
```

```text
----------------------------------------------------------------------------------------------------------------------
|ID| Product Name        | Brand          | Price          | Amount    | Screen    | RAM     | Memory    | Color     |
----------------------------------------------------------------------------------------------------------------------
|1 | Samsung S-5         | Samsung        | 2500,0 TL      | 25        | 5,5       | 8 GB    | 64 GB     | null      |
----------------------------------------------------------------------------------------------------------------------
|2 | Casper A3           | Casper         | 3000,0 TL      | 18        | 4,8       | 8 GB    | 128 GB    | null      |
----------------------------------------------------------------------------------------------------------------------

```

```text
---- Mobile Phone ----

1- List All Mobile Phones
2- Filter by id
3- Filter by brand
4- Delete by ID
5- Add product
0- Go back
Enter your choice: 1
```

