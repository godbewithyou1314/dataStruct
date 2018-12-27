# HashTable

## 什么是Hashtable？
Hash Table 是用一个hash函数将key对应到不同的分桶里边
1. 新插入一个元素时，hash函数将决定我们的key分忘哪个桶
2. 当检索时，使用相同的hash函数去定位

## 设计Hash Table

1. Hash Function
根据key值范围和需要的bucket数量去选择合适的Hash函数
2. Hash碰撞解决
    - 怎么组织同一个bucket里边的值
    - 如果过多的值都被分到同一个bucket怎么办
    - 怎么从一个bucket中寻值

很多时候都需要在key数量和bucket容量之间做一个权衡