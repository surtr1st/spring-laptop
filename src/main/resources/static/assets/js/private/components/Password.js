const REST_URL = 'http://localhost:8080/rest'

export default {
    name: 'Password',
    data() {
        return {
            msg: '',
            uid: '',
            account: {
                oldPassword: '',
                newPassword: '',
                rpPassword: ''
            },
            password: '',
            isSame: '',
            valid: ''
        }
    },
    methods: {
        async onCheckPassword() {
            const post = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: this.account.oldPassword
            }

            await fetch(`${REST_URL}/password/check`, post)

            this.getCheckedValue()
        },
        getCheckedValue() {
            fetch(`${REST_URL}/password/check`)
                .then(async res => {
                    this.isSame = await res.text()
                })
                .catch(e => console.log(e))
        },
        async onUpdatePassword() {
            if (this.account.newPassword <= 6 && this.account.newPassword >= 15) {
                this.valid = 'invalid'
                return
            }

            this.valid = 'valid'
            this.password = this.account.newPassword

            const patch = {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: this.password
            }

            await fetch(`${REST_URL}/update-password`, patch)
            this.onReset()
        },

        onReset() {
            this.account.oldPassword = ''
            this.account.newPassword = ''
            this.account.rpPassword = ''
        }
    },
    computed: {
        isMatch() {
            return (this.account.newPassword === this.account.rpPassword)
        }
    },
    created() {
        fetch(`${REST_URL}/uid`)
            .then(async res => {
                this.uid = await res.text()
            })
            .catch(e => console.log(e))
    },
    destroyed() {
        this.uid = ''
        this.account = {}
        this.password = ''
        this.isSame = false
    },
    template: /*html*/
    `
  <section class="row col-xl-10 col-lg-10 p-5">
    <div class="col-lg-8 m-auto overflow-hidden">
      <h2 class="py-3 text-primary text-center text-uppercase fw-bold">Change Password</h2>
      <h4 class="text-center fw-bold text-uppercase text-success">{{ msg }}</h4>
      <div class="py-5 px-5">
        <form @submit.prevent method="post">
          <div class="mb-3 row">
            <label for="oldPw" class="col-sm-4 col-form-label">Old
              Password</label>
            <div class="col-sm-7">
              <div class="row">
                 <div class="col-sm-8">
                    <input v-model="account.oldPassword" name="opw" type="password" class="form-control fw-bold"
                     id="oldPw">
                 </div>
                 <div class="col-sm-2">
                    <button @click="onCheckPassword()" class="btn btn-danger text-white">Check</button>
                 </div>
              </div>
            </div>
          </div>
          <div class="mb-3 text-center">
            <em v-if="isSame === 'true'" class="text-success fst-italic">Valid</em>
            <em v-else-if="isSame === 'false'" class="text-danger fst-italic">Invalid</em>
            <em v-else-if="isSame === ''" class="text-danger fst-italic"></em>
          </div>
          <div class="mb-3 row">
            <label for="nPw" class="col-sm-4 col-form-label">New
              Password</label>
            <div class="col-sm-6">
              <input v-model="account.newPassword" name="npw" type="password" class="form-control fw-bold"
                     id="nPw">
            </div>
          </div>
          <div class="mb-3 text-center">
            <em v-if="valid === 'valid' || valid === ''" class="text-danger fst-italic"></em>
            <em v-else-if="valid === 'invalid'" class="text-danger fst-italic">Password must have the length from 6 to 15!</em>
          </div>
          <div class="mb-3 row">
            <label for="rPw" class="col-sm-4 col-form-label">Repeat
              Password</label>
            <div class="col-sm-6">
              <input v-model="account.rpPassword" name="rpw" type="password" class="form-control fw-bold"
                     id="rPw">
            </div>
          </div>
          <div class="mb-3 text-center">
            <em v-show="!isMatch" class="text-danger fst-italic">Password doesn't match!</em>
          </div>
          <div class="mb-3 row">
            <div class="my-2 col text-center">
              <button :disabled="(isSame === 'true') ? false : true" @click="onUpdatePassword()" class="btn btn-lg btn-success">Update Password</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </section>
    `
}