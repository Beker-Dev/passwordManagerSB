<template>
  <div class="container">
    <h1 class="titulo">Editar senha de acesso para: {{password.url}}</h1>

    <div class="columns" v-if="notification.ativo">
      <div class="column is-12">
        <div :class="notification.classe">
          <button @click="onClickCloseNotification()" class="delete"></button>
          {{ notification.mensagem }}
        </div>
      </div>
    </div>


      <div class="field">
        <label class="label">Nome</label>
        <div class="control">
          <input class="input" type="text" v-model="password.name" :placeholder="password.name">
        </div>
      </div>

      <div class="field">
        <label class="label">URL</label>
        <div class="control">
          <input class="input" type="text" v-model="password.url" :placeholder="password.url">
        </div>
      </div>

      <div class="field">
        <label class="label">Descrição</label>
        <div class="control">
          <textarea class="textarea" rows="4" cols="50" v-model="password.description"
            :placeholder="password.description">
            </textarea>
        </div>
      </div>

      <div class="field">
        <label class="label">Senha</label>
        <div class="control">
          <input class="input" type="password" v-model="password.password" placeholder="senha">
        </div>
      </div>


    <div class="buttons">
      <button class="button is-danger" @click="onClickBack()">Voltar</button>
      <button class="button is-success" @click="onClickEdit()">Salvar</button>
    </div>
  </div>
</template>
  
<script lang="ts">
import { Vue } from 'vue-class-component';
import { Password } from '@/model/PasswordModel'
import { Notification } from '@/model/Notification'
import { PasswordClient } from '@/client/PasswordClient'
import { Prop } from "vue-property-decorator";
import { AuthUtils } from '@/utils/AuthUtils';


export default class passwordFormEditar extends Vue {
  private passwordClient!: PasswordClient
  private password: Password = new Password()
  private notification: Notification = new Notification()
  private authUtils: AuthUtils = new AuthUtils()


  @Prop({ type: Number, required: false })
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

  private onClickEdit(): void {
    this.passwordClient.update(this.password)
      .then(
        success => {
          this.notification = this.notification.new(true, 'notification is-success', 'Senha atualizada com sucesso!')
          this.onClickClean()
        }, error => {
          this.notification = this.notification.new(true, 'notification is-danger', 'Erro ao editar senha: ' + error)
          this.onClickClean()
        }
      )
  }

  private onClickBack(): void {
    this.$router.push({ name: 'password-detail', params: { id: this.password.id } })
  }

  private onClickCloseNotification(): void {
    this.notification = new Notification()
  }

  private onClickClean(): void {
    this.password = new Password()
  }

}
</script>
  
<style lang="scss">
@import "~bulma/bulma.sass";

.container {
  margin-left: 30px;
  width: 80%;
}

.titulo {
  display: flex;
  justify-content: center;
  font-size: 30px;
}

.enums {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-block-end: 20px;
}

.enum-tipo-atendimento {
  margin-left: 40px;
}

.botoes-form {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  width: 100%;
  margin-top: 20px;
  margin-block-end: 20px;
}

.btn-salvar {
  background-color: green;
  color: white;
  width: 40%;
}

.link-cad {
  width: 40%;
}

.btn-voltar {
  background-color: red;
  color: white;
  width: 100%;
}
</style>