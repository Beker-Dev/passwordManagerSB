<template>
  <div class="container">
    <h1 class="titulo" >Senha de acesso para: {{password.url}}</h1>

    <div class="columns" v-if="notification.ativo">
        <div class="column is-12">
          <div :class="notification.classe">
            <button @click="onClickCloseNotification()" class="delete" ></button>
            {{ notification.mensagem }}
          </div>
        </div>
      </div>

    <div class="field">
      <div class="control">
        <i>Id: {{password.id}}</i>
      </div>
    </div>
    <div class="field">
      <div class="control">
        <i>Nome: {{password.name}}</i>
      </div>
    </div>
    <div class="field">
      <div class="control">
        <i>Descrição: {{password.description}}</i>
      </div>
    </div>
    <div class="field">
      <div class="control">
        <i>URL: {{password.url}}</i>
      </div>
    </div>
    <div class="field">
      <div class="control">
        <i>Senha: {{password.password}}</i>
      </div>
    </div>

    <div class="buttons">
      <button class="button is-warning" @click="onClickBack()">Voltar</button>
      <button @click="onClickEditPage(password.id)" class="button is-link">Editar</button>
      <button class="button is-danger" @click="onClickDelete()">Excluir</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import { PasswordClient } from '@/client/PasswordClient'
import { Password } from '@/model/PasswordModel'
import { Notification } from '@/model/Notification'
import { AuthUtils } from '@/utils/AuthUtils'


export default class passwordFormDetalhar extends Vue {
  private passwordClient!: PasswordClient
  private password: Password = new Password()
  private notification: Notification = new Notification()
  private authUtils: AuthUtils = new AuthUtils() 


  @Prop({type: Number, required: false})
  private readonly id!: number

  
  public mounted(): void {
    this.redirectPage()
    this.passwordClient = new PasswordClient()
    this.getPassword()
  }

  public redirectPage(): void {
        var authenticated = this.authUtils.checkAuthenticated()
        if (!authenticated) {
            this.$router.push({ name: 'user-login' })
        }
    }

  private getPassword(): void {
    this.passwordClient.findById(this.id)
        .then(
            success => {
              this.password = success
            },
            error => console.log(error)
        )
  }

  private onClickEditPage(id: number){
    this.$router.push({ name: 'password-edit', params: { id: id } })
  }

  private onClickBack(): void {
        this.$router.push({ name: 'password' })
  }

  private onClickDelete(): void {
    var deleteConfirmation = confirm("Confirma a remoção da senha de acesso para " + this.password.url + "?")
    if (deleteConfirmation) {
      this.passwordClient.delete(this.password)
      .then(
            success => {
                this.notification = this.notification.new(true, 'notification is-success', 'Senha removida com sucesso!')
            }, error => {
                this.notification = this.notification.new(true, 'notification is-danger', 'Erro ao remover a senha: ' + error)
            }
        )
    }
  }

  private onClickCloseNotification(): void {
      this.notification = new Notification()
  }
}

</script>
<style lang="scss">
@import "~bulma/bulma.sass";
.container{
  margin-left: 30px;
  width: 80%;
}
.titulo{
  display: flex;
  justify-content: center;
  font-size: 30px;
}
.enums{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-block-end: 20px;
}
.botoes-form{
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  width: 100%;
  margin-top: 20px;
  margin-block-end: 20px;
}
.btn-editar{
  background-color: blue;
  color: white;
  width: 30%;
}
.link-voltar{
  width: 30%;
}
.btn-voltar{
  background-color: dimgrey;
  color: white;
  width: 100%;
}
.btn-desativar{
  background-color: red;
  color: white;
  width: 30%;
}
</style>