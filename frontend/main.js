// Vue.component('offersElement', {
//
//     template: `
//     <ul class="list-unstyled">
//         <li v-for="offer in offers">
//             <div class="card">
//                 <div class="card-title">{{offer.title}}</div>
//                 <div class="card-body">{{offer.text}}</div>
//                 <div class="card-footer"><button class="btn btn-success">Take offer!</button></div>
//             </div>
//         </li>
//     </ul>
//     `,
//     data() {
//         return {
//             offers: [
//                 {
//                     id: 1,
//                     title: "ponuda 1",
//                     text: "ovaa ponuda vazhi samo vo period od 23 juni do 23 juli, zemete sega ili nikogash poekje!"
//                 }
//             ]
//         }
//     }
// })

var app = new Vue({
    el: '#app',
    data:{
        offers: [
            {
                id: 1,
                title: "ponuda 1",
                text: "ovaa ponuda vazhi samo vo period od 23 juni do 23 juli, zemete sega ili nikogash poekje!"
            },
            {
                id: 2,
                title: "ponuda 2",
                text: "ovaa ponuda vazhi samo vo period od 23 juni do 23 juli, zemete sega ili nikogash poekje!"
            }
        ],
        news:[
            {
                id:1,
                title: "The machine for leg curls is broken",
                text: "due to malfunctions, the machine will we be out of work for 1 week",
                date: null
            }
        ]
    },
    methods:{
        fillDate: function () {
            this.news.date = "2"
        }
    }

})

var jumbotron = new Vue({
    el: '#jumbotron',
    data:{
        loggedIn: false,
        user: ""
    },
    methods:{
        logIn: function () {
            this.loggedIn = true,
            this.user = "Mare"
        },
        logOut: function () {
            this.loggedIn = false,
                this.user = ""
        }
    }
})