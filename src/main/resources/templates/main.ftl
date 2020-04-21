<#import "parts/common.ftl" as c>

<@c.page>
<div class="aud-box">
        <div id="selector" class="aud-box-header">
                <label>Корпус</label>
                <select v-model="building">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
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
                <h4>Выберите аудиторию</h4>
        </div>
        <div id="app"></div>
</div>
<div id="menu"></div>
</@c.page>