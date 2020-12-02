`use strict`

let displaySection = document.getElementById("reimbursement_display");

async function getInfo() {
    let response = await fetch(`./reimbursement_info`);
    let reimbursements = await response.json();
    for(let reimbursement of reimbursements) {
        displaySection.addEventListener('load', () => {
            infoPopulator(reimbursement)();
        })
    }
}

getInfo();

function infoPopulator(reimbursement) {
    return async ()=> {
        displaySection.innerHTML = '';
        let infoList = document.createAttribute('ul');

        displaySection.appendChild(infoList);
        for (let key in reimbursement) {
            if (['reimbursementid', 'amount', 'submitted', 'resolved', 'description', 'author', 'resolver', 'status', 'type'].includes(key)) {
                let listItem = document.createElement('li');
                listItem.innerText = `${key}: ${reimbursement[key]}`;
                infoList.appendChild(listItem);
            }
        }
    }
}