

###访问配置信息的URL与配置文件的映射关系如下：

    /{application}/{profile}[/{label}]
    /{application}-{profile}.yml
    /{label}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{label}/{application}-{profile}.properties

    ，其中{label}对应Git上不同的分支，默认为master


    demo 通过浏览器访问:
    http://localhost:1201/config-client/dev/master
    http://localhost:1201/config-client/prod/master

    {
        name: "config-client",
        profiles: [
         "prod"
        ],
        label: null,
        version: null,
        state: null,
        propertySources: [
            {
            name: "https://github.com/zhaojun2066/config-repo-demo/config-client-prod.yml",
            source: {
                 info.profile: "prod"
            }
        },
        {
        name: "https://github.com/zhaojun2066/config-repo-demo/config-client.yml",
        source: {
           info.profile: "default"
         }
        }
        ]
    }


