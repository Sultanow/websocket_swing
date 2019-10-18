<template>
  <div class="hello">
    <textarea v-model="msg" placeholder="add multiple lines"></textarea>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  mounted:function(){
    this.connect();
  },
  methods: {
    connect() {
      this.socket = new WebSocket("ws://localhost:1337/");
      this.socket.onopen = () => {
        alert('opened');
        this.status = "connected";   
        //this.socket.onmessage = ({data}) => {};
      };
    },
    disconnect() {
      this.socket.close();
      this.status = "disconnected";
      this.logs = [];
    },
    sendMessage(e) {
      this.socket.send(e);
    }
  },
  watch: {
    msg: function(val) {
      this.sendMessage(val);
    }
  }
}
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
