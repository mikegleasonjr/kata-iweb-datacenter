# kata-iweb-datacenter

## Context

At [iWeb Technologies](http://iweb.com/), we send a little technical test to those applying for a job. I decided to take the challenge myself and write it in TDD to learn it and practice it.

## Technical Test Description

Write a simple application that distributes virtual machines on servers in a datacenter. The application can be written in any language.

### Some details

- A server have some space to host virtual machines (capacity)
- When hosted on a server, a virtual machine occupy a certain amount of space
- The number of virtual machines a server can host vary with the server size and the virtual machines size
- The application must receive some servers and some virtual machines to distribute on them as input

### Specifications

- The load of the virtual machines on the server must be as balanced as possible
- Servers have a defined space and cannot host virtual machines beyond their capacity

### Example Input

```javascript
{
    "servers": [{
        "id": "server1",
        "capacity": 4
    }, {
        "id": "server2",
        "capacity": 6
    }],
    "virtualMachines": [{
        "id": "VM1",
        "size": 1
    }, {
        "id": "VM2",
        "size": 4
    }, {
        "id": "VM3",
        "size": 2
    }]
}
```

### Example Output

```javascript
{
    "servers": [{
        "id": "server1",
        "capacity": 4,
        "usePercentage": 75,
        "virtualMachines": [{
            "id": "VM1",
            "size": 1
        }, {
            "id": "VM3",
            "size": 2
        }]
    }, {
        "id": "server2",
        "capacity": 6,
        "usePercentage": 67,
        "virtualMachines": [{
            "id": "VM2",
            "size": 4
        }]
    }]
}
```