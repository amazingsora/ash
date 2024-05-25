# ash

2024/05
SpringBoot2.7.1升級3.2.6
JDK18->17->21

升級思路
由於當前JDK版本為18為配合長期維護版因此先將低版本為17後升級為21


STEP1.JDK降板
Pom檔設定調整
 ![image](https://github.com/amazingsora/ash/assets/56026769/dc12348d-faec-45e0-8464-6b5804dde2ae)

STEP2.升級Spring3.2.6
1.	POM檔修改(舊依賴設定調整)
 ![image](https://github.com/amazingsora/ash/assets/56026769/e084f365-0373-4d11-8e22-b0b65be5caa3)


Jstl指定版號
 ![image](https://github.com/amazingsora/ash/assets/56026769/97a8ea12-9e5d-43ab-af9d-81878f2ae98f)
MySQL設定調整
 ![image](https://github.com/amazingsora/ash/assets/56026769/158c6e0a-c816-4136-afff-3353e46ca677)
ORM相關調整(Spring3.0改為jakarta),可能不用改
 ![image](https://github.com/amazingsora/ash/assets/56026769/3b3e1e2d-1f74-4f53-935b-7e8b85061a41)
Httpclient改為Httpclient5
 ![image](https://github.com/amazingsora/ash/assets/56026769/1db2fd8f-6dee-42d8-8d69-92a94264cd82)




2.	程式調整
JavaEntity
由javax->Jakarta
 ![image](https://github.com/amazingsora/ash/assets/56026769/0eebdecf-f17f-4163-953d-d2880bdb3066)
Eclipse會出現xml配置錯誤訊息(該專案未用xml配置)，可忽視
 ![image](https://github.com/amazingsora/ash/assets/56026769/f43b1820-ffe4-4d3a-84c4-13a16ef1d15a)
Httpclient相關元件調整
 ![image](https://github.com/amazingsora/ash/assets/56026769/bbc359b9-6955-4c88-ac20-52849c990a13)


 
STEP3.JDK21升級
Pom檔調整
*標籤修改，舊標籤可能IDE會不認JDK21
 ![image](https://github.com/amazingsora/ash/assets/56026769/388c3052-d048-47d5-bcb6-f06a984787a4)



