<template>
  <div class="container">
    <h1 class="titulo" >Senha de acesso para: {{password.url}}</h1>
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
      <button class="button is-danger">Excluir</button>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component'
import { Prop } from 'vue-property-decorator'
import { PasswordClient } from '@/client/PasswordClient'
import { Password } from '@/model/PasswordModel'

export default class passwordFormDetalhar extends Vue {
  private passwordClient!: PasswordClient
  private password: Password = new Password()

  @Prop({type: Number, required: false})
  private readonly id!: number

  
  public mounted(): void {
    this.passwordClient = new PasswordClient()
    this.getPassword()
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