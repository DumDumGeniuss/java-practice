Synchronized
===========================

## 說明：
<br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;假設我今天有一個物件叫做Bank，但是我我每次執行
程式的時候我只允許一間銀行存在，這時候我就必須要實作Singleton，我把 constructor 設置成private，
並且只給予使用者 getInstance 方法去取得物件，目前為止看起來非常的完美，達到了我要的目的，但這只是
在單線程的情況下，多線程呢？
<br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果今天有好幾個線程同時執行，他們都會需要取得Bank物件
來做事情，而我的singleton方法實作方式是當我偵測到物件還沒有被實體化而使用者需要這個物件時，我會產生一個新的
Bank物件，反之則給他已經產生好的，這時候會有一個問題就是，假設兩個線程同一時間判斷Bank還沒被實體化，而
同時通過了這道防線同時產生新物件，這樣就會同時產生了兩個Bank，違反了singleton的原則。
<br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要處理此一問題很簡單，只要把 getInstance() 方法加上
Synchronized符號讓他變成 Synchronized method 就行了，這個符號代表當已經有一個線程在執行這支方法，其他線程就必須等待這支程式完成動作
之後才能執行它，簡單來說就是握有key的人才可以進入，而其他人只能在上一個人釋出這個key的時候搶奪，拿到的
人才有權力接著執行這個方法。
<br/>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;又假設我今天有個方法叫做 getMoney()，就如字面上的意思他是提款
，我們要避免使用者在沒錢的情況下還做提款動作，這時想當然我們直接在methond上面宣告 Synchronized符號即可，但是
如果這個method裡面要做的事情不只一個呢？如果提款這個動作只佔這個方法執行時間的 10% 呢？這時候就可以用Synchronized
statement 來解決我們的問題了， 這個東西的好處就是我們只需要把重要的code鎖在一起，當有線程進入這個Synchronized statement範圍時
，他就擁有了這段程式碼的暫時擁有權，此時其他線程就不允許去執行這段程式碼，直到他把key釋出為止。
