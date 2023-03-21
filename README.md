# Cheque

Консольное приложение должно формировать чек по входным парамитрам вида "itemId-quantity itemId-quantity card-cardNumber"(напимер 3-15 452-2 7-3 card-1234567). Данные о товарах и дисконтных карт задаются в коде и сохраняются в файлах, их можно обновить, запустив программу без входных параметров, или вывести на консоль, введя в качестве параметра имя файла (items.dat или discountCards.dat). Среди товаров предусмотренны акционные. При использовании дисконтной карты или наличии в чеке более пяти единиц акционного товара начисляются скидки, что отображается в чеке.
В работе применены паттерны проектирования Command и Builder, система сборки gradle, юнит-тесты.
