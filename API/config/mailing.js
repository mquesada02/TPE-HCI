// https://ethereal.email/
module.exports = {
    service: {
        host: 'smtp.ethereal.email',
        port: 587,
        secure: false,
        user: 'letha.beatty@ethereal.email',
        password: 'v7G9Ky7d5Na8uZNUBP'
    },
    registerVerification: {
        from: 'Exercise <noreply@exercise.com>',
        subject: 'Email Confirmation',
        htmlBodyTemplate:
            `<div>
                <h1 style="text-align: center;">
                    <strong>Welcome to <span style="color: #fc987e;">Fiit-web</span></strong>
                </h1>
                <p>
                    <span>Valid until <%EXPIRATION_DATE%></span>
                </p>
                <div>
                    <a href="<%CONFIRM_LINK%>">Click para confirmar</a>
                <div>
                <h1 style="text-align: center;">
                    <strong>Your code is <span style="color: #fc987e;"><%CODE%></span></strong>
                </h1>
            </div>`,
        confirmationLink: 'http://localhost:3000/verify?email=<%EMAIL%>&code=<%CODE%>&user=<%USERNAME%>',
        codeExpirationInterval: 24 * 60 * 60 * 1000
    }
}