<template>
    <div class="container">
        <div class="columns" v-if="notification.ativo">
            <div class="column is-12">
                <div :class="notification.classe">
                    <button @click="onClickCloseNotification()" class="delete"></button>
                    {{ notification.mensagem }}
                </div>
            </div>
        </div>

        <div class="field">
            <p class="control has-icons-left">
                <input class="input" type="password" placeholder="Senha Atual" v-model="actualPw">
                <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                </span>
            </p>
        </div>
        <div class="field">
            <p class="control has-icons-left">
                <input class="input" type="password" placeholder="Nova Senha" v-model="newPw">
                <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                </span>
            </p>
        </div>
        <div class="field">
            <p class="control has-icons-left">
                <input class="input" type="password" placeholder="Confirmar Nova Senha" v-model="confirmPw">
                <span class="icon is-small is-left">
                    <i class="fas fa-lock"></i>
                </span>
            </p>
        </div>
        <div class="field">
            <p class="control">
                <div class="buttons">
                <button class="button is-link" @click="onClickUpdatePw()">
                    Editar Senha
                </button>
                </div>
            </p>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import { Notification } from '@/model/Notification'
import { User } from '@/model/UserModel';
import { UserClient } from '@/client/UserClient';
import { AuthUtils } from '@/utils/AuthUtils';
import { getCookie, removeCookie } from 'typescript-cookie';

export default class UserRegister extends Vue {
    private notification: Notification = new Notification()
    private userClient!: UserClient
    private user: User = new User()
    private authUtils: AuthUtils = new AuthUtils()
    private actualPw: String
    private newPw: String
    private confirmPw: String

    public mounted(): void {
        this.redirectPage()
        this.userClient = new UserClient()
        this.getUser()
    }

    public redirectPage(): void {
        var authenticated = this.authUtils.checkAuthenticated()
        if (!authenticated) {
            this.$router.push({ name: 'user-login' })
        }
    }

    private onClickCloseNotification(): void {
        this.notification = new Notification()
    }

    private onClickUpdatePw(): void {
        if (this.checkPw()) {
            this.user.password = (this.actualPw as any)
            this.user.newPw = (this.newPw as any)
            this.userClient.updatePassword(this.user)
            .then(
                success => {
                    this.user = success.data
                    this.notification = this.notification.new(true, 'notification is-success', 'Senha editada com sucesso!')
                    removeCookie("access")
                    this.$router.push({ name: 'user-login' })
                }, error => {
                    this.notification = this.notification.new(true, 'notification is-danger', 'Erro ao editar senha ' + error)
                    this.onClickClean()
                }
            )
        }
    }

    private checkPw(): boolean {
        if (this.newPw != this.confirmPw) {
            this.notification = this.notification.new(true, 'notification is-danger', 'Senhas nao conferem')
            this.onClickClean()
            return false
        }
        return true
    }

    private getUser(): void {
        this.user.id = (getCookie("access") as any)
        this.userClient.findById(this.user.id)
        .then(
            success => {
              this.user= success
            },
            error => console.log(error)
        )
    }

    private onClickClean(): void {
        this.user = new User()
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