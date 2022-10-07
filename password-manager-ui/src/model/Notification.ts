export class Notification {

    ativo! : boolean
    classe!: string
    mensagem!: string

    public new(ativo: boolean, classe: string, mensagem: string): Notification {

        const notification : Notification = new Notification()

        notification.ativo = ativo
        notification.classe = classe
        notification.mensagem = mensagem

        return notification
    }
}