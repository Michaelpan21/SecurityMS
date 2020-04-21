
var audienceApi = Vue.resource('/audience{/id}');

Vue.component('audiences-menu', {
  props: ['number', 'id', 'isShown'],
  template:
    '<div v-if="isShown == true" class="menu">' +
        '<div class="menu-header">' +
            '<button class="close-btn" @click="closeMenu">X</button></div>' +
        '<h3>Аудитория {{number}}</h3>' +
        '<button @click="openAudience">Получить доступ</button>' +
        '<button @click="closeAudience">Освободить</button>' +
    '</div>',
  methods: {
    closeMenu: function() {
        menu.isShown = false;
    },
    openAudience: function() {
        audienceApi.update({id: this.id}, this.id).then(response => {
          app.audiences.forEach(item => {
            if(item.id == response.body.id) {
              item = response.body;
              console.log(response.body.number);
            }
          });
        });
    },
    closeAudience: function() {
        audienceApi.update(id);
    }
  }
});

Vue.component('audiences-row', {
  props: ['audience'],
  template: '<button class="a-box" @click="openMenu">' +
                '<span>{{audience.number}}</span><div v-if="audience.principal_id > 0">{{audience.principal_id}}</div>' +
            '</button>',
  methods: {
    openMenu: function() {
        menu.isShown = true;
        menu.number = this.audience.number;
        menu.id = this.audience.id;
    }
  }
});

Vue.component('audiences-list', {
  props: ['audiences'],
  template:
    '<div class="grid-container">' +
         '<audiences-row v-for="audience in audiences" :key="audience.id" :audience ="audience"/>' +
    '</div>'
});



var app = new Vue({
  el: '#app',
  template: '<audiences-list :audiences ="audiences"/>',
  data: {
    audiences: [],
  },
});

var menu = new Vue({
  el: '#menu',
  template: '<audiences-menu :number ="number" :id="id" :isShown="isShown"/>',
  data: {
    number: null,
    id: null,
    isShown: false,
  }
});

var selector = new Vue({
  el: '#selector',
  data: {
    building: null,
    floor: null,
  },
  methods: {
    find: function() {
      audienceApi.get({building: this.building, floor: this.floor}).then(response => {
        app.audiences = response.body;
      });
    },
  }
});