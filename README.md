# OpenStackRestWrapper
REST for the OpenStack (mostly for immerse-cloud) that allows manipulate more easily with OpenStack API (Xena).

---

## Why this project was created?
This project was created for several reasons.
1. In order to learn how to use spring in practice
2. Because OpenStack may seem very complex for newbies, and they may want have simple api only for manipulating servers

---

## How to use this "API"?
**This instruction assumes that you have already installed this Spring application, and configured application.properties**

**Volumes**

Get all volumes: `localhost:8080/volumes` **GET**

**Network**

Get all networks: `localhost:8080/networks` **GET**

**Flavor**

Get all flavors: `localhost:8080/flavors` **GET**

**Keypair**

1. Get all keypairs: `localhost:8080/keypairs` **GET**. **Attention!** this may return empty list on immerse cloud, and you must manually create new keypair instead of using existing keypair.


2. Create keypair: `localhost:8080/keypairs/create` **POST**

   Request parameters:
    
   `name` - Name of keypair. Must be unique.
    
   `public-key` _(Optional)_ - Predefined public key of keypair.
    
   **Examples:** 
    
   `localhost:8080/keypairs/create?name=Test`,
    
   `localhost:8080/keypairs/create?name=Test1&public-key=some_long_public_key_by_you`
    
**Server**

1. Get all servers: `localhost:8080/servers` **GET**


2. Stop existing server by **server-id**: `localhost:8080/servers/stop?server-id=123` **GET**


3. Delete existing server by **server-id**: `localhost:8080/servers/delete?server-id=123` **GET**


4. Action with existing server by **server-id**:

   `localhost:8080/servers/action?server-id=123&action=PAUSE` **GET**
   
   Existing actions:

   | Action         | Description                                     |
   |----------------|-------------------------------------------------|
   | PAUSE          | Pause the server                                |
   | UNPAUSE        | Un-Pause the server                             |
   | STOP           | Stop the server                                 |
   | START          | Start the server                                |
   | LOCK           | Lock the server                                 |
   | UNLOCK         | Unlock a locked server                          |
   | SUSPEND        | Suspend the server                              |
   | RESUME         | Resume a suspended server                       |
   | RESCUE         | Rescue the server                               |
   | UNRESCUE       | Un-Rescue the server                            |
   | SHELVE         | Shelve the server                               |
   | SHELVE_OFFLOAD | Remove a shelved instance from the compute node |
   | UNSHELVE       | Un-Shelve the server                            |

5. Create server `localhost:8080/servers/create` **POST**

   Parameters:
   
   * `name` - The name of new server

   * `flavor-id` - Flavor id of new server (In simple terms, server hardware configuration)

   * `image-id` _(Optional)_ - Image id for creating server instance

   * `networks` - Networks, may be passed multiple. 
    
       Example: `localhost:8080/servers/create?networks=first&networks=second`

   * `keypair` _(Optional)_ - Keypair for server

   * **Additional parameters for volume:**

     `uuid` - Unique id of the volume

     `deviceName` - Name for device, for example: /dev/vda (**KVM**)
      
         Note: The device name unfortunatelly matters, and even worse - it depends on the hypervisor you use. In our experience - for KVM it is /dev/vda, for Xen it is /dev/xvda.

     `order` _(Optional, by default 0)_ - Boot order (_index_) of volume

   Examples:

   * `http://localhost:8080/servers/create?name=Test&flavor-id=d1e6c23d-764d-4073-b7d0-3cdcb1d169f0&networks=614e0cf0-0000-1234-86ad-9a4f908b8d7b&keypair=Test&uuid=bb293f23-150d-4997-1337-f39f2f3f8eb7&deviceName=/dev
   /vda&order=0`

   * `http://localhost:8080/servers/create?name=Test&flavor-id=d1e6c23d-764d-4073-b7d0-3cdcb1d169f0&networks=614e0cf0-0000-1234-86ad-9a4f908b8d7b&keypair=Test&image-id=bb293f23-150d-4997-1337-f39f2f3f8eb7`


---
### About me and this project:
This is my first full Spring application. The main goal of the project is to learn the basic concepts of Spring.

### OpenStack library

https://openstack4j.github.io