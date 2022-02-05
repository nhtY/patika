# VeriYapilariVeAlgoritmalar_Proje3_Patika
Kodluyoruz eğitimi kapsamında veri yapıları ve algoritmalar eğitimi projesi.

Proje 3
[7, 5, 1, 8, 3, 6, 0, 9, 4, 2] dizisinin Binary-Search-Tree aşamalarını yazınız.

Örnek: root x'dir. root'un sağından y bulunur. Solunda z bulunur vb.
---
# Cevap:

Dizideki elemanların tree'ye teker teker eklendiğini varsayarsak:
* left: null root: 7 right: null
.
.
.
*                root: 7
         left: 5     right: null

*                      root: 7
            left: 5             right: null
        left:1  right: null
*                       root: 7
                L: 5             R: 8
            L:1    R: null    L: null   R: null 
*                       root: 7
                L: 5             R: 8
            L:1    R: null    L: null   R: null
        L:null R: 3
*                       root: 7
                L: 5             R: 8
            L:1    R: 6     L: null   R: null
        L:null R: 3
*                       root: 7
                L: 5             R: 8
            L:1    R: 6     L: null   R: null
        L:0 R: 3
*                       root: 7
                L: 5             R: 8
            L:1    R: 6     L: null   R: 9
        L:0 R: 3
*                       root: 7
                L: 5             R: 8
            L:1    R: 6     L: null   R: 9
        L:0     R: 3
            L:null R:4
*                       root: 7
                L: 5             R: 8
            L:1    R: 6     L: null   R: 9
        L:0     R: 3
            L:2     R:4