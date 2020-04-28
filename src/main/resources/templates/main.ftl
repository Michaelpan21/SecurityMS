<#import "parts/common.ftl" as c>

<@c.page>
<div class="aud-box">
    <div id="selector" class="aud-box-header">
         <div class="sel-box">
              <label>Корпус</label>
              <select v-model="building">
                   <option>1</option>
                   <option>2</option>
                   <option>3</option>
                   <option>5</option>
              </select>
              <label>Этаж</label>
              <select v-model="floor">
                   <option>1</option>
                   <option>2</option>
                   <option>3</option>
                   <option>4</option>
              </select>
              <button type="submit" @click="find">Найти</button>
         </div>
         <h4>Выберите аудиторию</h4>
    </div>
    <div id="app"></div>
</div>
<div class="helper-box">
     <h6>Ваши пары на сегодня:</h6>
     <div id="helper"></div>
</div>
<div id="menu"></div>

</@c.page>