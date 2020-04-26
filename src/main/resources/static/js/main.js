
var audienceApi = Vue.resource('/audience{/id}');
var scheduleApi = Vue.resource('/schedule{/id}');

Vue.component('schedule-line', {
  props: ['sLine'],
  template: '<div>'+
              '<h6>пара</h6>' +
              '<div>' +
                '<b>{{sLine.subject_type}}</b> - {{sLine.subject}}' +
                '<i> - Б.М. {{sLine.audience}}</i>' +
              '</div>' +
              '<small>Преподаватель: {{sLine.professor}} - {{sLine.prof_degree}}  Группа: {{sLine.group}}</small>' +
            '</div>',
});

Vue.component('audiences-menu', {
  props: ['audience', 'isShown', 'schedule'],
  template:
    '<div v-if="isShown == true" class="menu">' +
      '<div class="menu-header">' +
        '<button class="close-btn" @click="closeMenu">X</button></div>' +
        '<h3>Аудитория {{audience.number}}</h3>' +
        '<schedule-line v-for="sLine in schedule" :key="sLine.s_id" :sLine ="sLine"/>' +
        '<button @click="openAudience">Получить доступ</button>' +
        '<button @click="closeAudience">Освободить</button>' +
    '</div>',
  methods: {
    closeMenu: function() {
      menu.isShown = false;
    },
    openAudience: function() {
      audienceApi.update({id: this.audience.id}, {action: 'reserve'}).then(response => {
        if (response.ok) {
          audienceApi.get({building: this.audience.building, floor: this.audience.floor}).then(response => {
            app.audiences = response.body;
          });
        }
        this.closeMenu();
      });
    },
    closeAudience: function() {
      audienceApi.update({id: this.audience.id}, {action: 'leave'}).then(response => {
        if (response.ok) {
          audienceApi.get({building: this.audience.building, floor: this.audience.floor}).then(response => {
            app.audiences = response.body;
          });
        }
        this.closeMenu();
      });
    },
  }
});

Vue.component('audiences-button', {
  props: ['audience'],
  template: '<button v-bind:class="classObject" @click="openMenu">' +
              '<h5>{{audience.number}}</h5>' +
              '<div v-if="audience.s_id != undefined"><p>{{audience.subject_type}} - {{audience.subject}}</p></div>' +
            '</button>',
  computed: {
    classObject: function() {
      return {
        'a-box': this.audience.principal_id != 0,
        'a-box-reserved': this.audience.principal_id > 0
      }
    },
  },
  methods: {
    openMenu: function() {
      menu.isShown = true;
      menu.audience = this.audience;
    },
  }
});

Vue.component('audiences-list', {
  props: ['audiences'],
  template:
    '<div class="grid-container">' +
      '<audiences-button v-for="audience in audiences" :key="audience.id" :audience ="audience"/>' +
    '</div>',

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
  template: '<audiences-menu :audience="audience" :isShown="isShown" :schedule="schedule"/>',
  data: {
    audience: null,
    isShown: false,
    schedule: [],
  },
  watch: {
    audience: function(newAudience, oldAudience) {
      scheduleApi.get({id: this.audience.id}).then(response => {
        this.schedule = response.body;
      });
    },
  },
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