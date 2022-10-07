<template>
    <div class="container">
        <h1 class="titulo">Cadastrar Senha</h1>

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
                <input class="input" type="text" v-model="password.name" placeholder="Nome">
            </div>
        </div>

        <div class="field">
            <label class="label">URL</label>
            <div class="control">
                <input class="input" type="text" v-model="password.url" placeholder="https://google.com">
            </div>
        </div>

        <div class="field">
            <label class="label">Descrição</label>
            <div class="control">
            <textarea class="textarea" rows="4" cols="50" v-model="password.description" placeholder="Escreva algo sobre a senha aqui">
            </textarea>
            </div>
        </div>

        <div class="field">
            <label class="label">Senha</label>
            <div class="control">
                <input class="input" type="password" v-model="password.password" placeholder="Senha">
            </div>
        </div>

        <div class="buttons">
            <button class="button is-danger" @click="onClickBack()">Voltar</button>
            <button class="button is-success" @click="onClickRegister()">Salvar</button>
        </div>
    </div>
</template>
  
<script lang="ts">
import { Vue } from 'vue-class-component';
import { Notification } from '@/model/Notification'
import { Password } from "@/model/PasswordModel";
import { PasswordClient } from "@/client/PasswordClient";
import { User } from '@/model/UserModel';
import { getCookie } from "typescript-cookie";
import { AuthUtils } from '@/utils/AuthUtils';


export default class passwordForm extends Vue {
    private passwordClient!: PasswordClient
    private password: Password = new Password()
    private notification: Notification = new Notification()
    private user: User = new User()
    private authUtils: AuthUtils = new AuthUtils()


    public mounted(): void {
        this.redirectPage()
        this.passwordClient = new PasswordClient()
        this.getUser()
    }

    public redirectPage(): void {
        var authenticated = this.authUtils.checkAuthenticated()
        if (!authenticated) {
            this.$router.push({ name: 'user-login' })
        }
    }

    private getUser(): void {
        this.user.id = (getCookie("access") as any)
        console.log(this.user.id)
        this.password.user = this.user
    }

    private onClickRegister(): void {
        this.passwordClient.save(this.password)
            .then(
                success => {
                    this.notification = this.notification.new(true, 'notification is-success', 'Senha cadastrada com sucesso!')
                    this.onClickClean()
                }, error => {
                    this.notification = this.notification.new(true, 'notification is-danger', 'Erro ao cadastrar senha: ' + error)
                    this.onClickClean()
                })
    }

    private onClickCloseNotification(): void {
        this.notification = new Notification()
    }

    private onClickClean(): void {
        this.password = new Password()
    }

    private onClickBack(): void {
        this.$router.push({ name: 'password' })
    }
}
</script>
  
<style lang="scss">
@import "~bulma/bulma.sass";

.container {
    margin-left: 30px;
    width: 80%;
}

.divisoes {
    display: flex;
    flex-direction: row;
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