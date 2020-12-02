`use strict`

//let displaySection = document.getElementById("reimbursement_display");
let loadInfoButton = document.getElementById("loadInfoButton");

//`https://pokeapi.co/api/v2/pokemon/1`);//

async function getInfo() {
    let response = await fetch(`./reimbursement_info_pending`);
    let reimbursements = await response.json();
    console.log(reimbursements)
    loadInfoButton.addEventListener('click', () => {
        infoPopulator(reimbursements)();
    });
}

getInfo();

function infoPopulator(reimbursement) {
    return async ()=> {
        displaySection.innerHTML = '';
        let infoList = document.createElement('ul');

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