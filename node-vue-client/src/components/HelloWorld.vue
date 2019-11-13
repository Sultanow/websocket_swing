<template>
  <div id="step_form">
    <div id="person">
      <input v-model="data_last" placeholder="Name">
      <input v-model="data_first" placeholder="Vorname">
      <button v-on:click="searchPerson();">Suchen</button>
    </div>

    <div id="search_result">
      <select size=10 v-model="data_result_selected">
        <option v-for="(item, index) in data_result" :value="item" :key="index">{{item}}</option>
      </select>
    </div>

    <div id="send_to_allegro">
      <button v-on:click="sendMessage(data_result_selected, 'textfield');">Nach ALLEGRO &uuml;bernehmen</button>
    </div>

    <div id="textarea">
      <textarea rows="4" v-model="content_textarea" placeholder="add multiple lines"></textarea>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    last: String,
    first: String,
    result_selected: String,
    content_textarea: String
  },
  data: function() {
    return {
      data_last: this.last,
      data_first: this.first,
      data_result: [],
      data_result_selected: this.selected_result,
      search_space: [['Hans', 'Mayer'], ['Linda', 'Reitmayr'], ['Karl', 'May'], ['Jens', 'Mueller'], ['Steffi', 'Ruckmueller']]
    };
  },
  mounted:function(){
    this.connect();
  },
  methods: {
    connect() {
      this.socket = new WebSocket("ws://localhost:1337/");
      this.socket.onopen = () => {
        this.status = "connected";   
        //this.socket.onmessage = ({data}) => {};
      };
    },
    disconnect() {
      this.socket.close();
      this.status = "disconnected";
      this.logs = [];
    },
    searchPerson() {
      this.data_result = [];
      for(let i = 0; i < this.search_space.length; i++) {
        let element = this.search_space[i];
        if (element[1].toLowerCase().indexOf(this.data_last.toLowerCase()) >= 0) {
          this.data_result.push(element[0] + " " + element[1]);      
        }
      }
    },
    sendMessage(e, target) {
      this.socket.send(JSON.stringify({ target: target, content: e }));
    }
  },
  watch: {
    content_textarea: function(val) {
      this.sendMessage(val, "textarea");
    }
  }
}
</script>

<style scoped>
*{
  box-sizing: border-box;
}

div#step_form {
  position: relative;
  border: 1px solid red;
  background-color: #ffffff;
  margin: 100px;
  height: 400px;
}
div#step_form div {
  border: 1px solid lightgrey;
  width: 400px;
  margin: 10px;
  padding: 4px 4px 4px 0;
}
div#step_form div input, button, textarea, select {
  margin-left: 4px;
}
select, textarea {
  margin: 0 4px 0 4px;
  padding: 0;
  width: 390px;
}

div#person {
  position: relative;
  text-align: left;
}
div#person button {
  position: absolute;
  right: 4px;
}
div#person input {
  align: left;
  right: 0px;
}

div#send_to_allegro {
  text-align: right;
}
div#send_to_allegro button {
  display: inline-block;
}

div#textarea {
  position: absolute;
  bottom: 0px;
}
</style>