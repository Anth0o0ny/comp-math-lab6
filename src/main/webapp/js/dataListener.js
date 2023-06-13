const x0Input = document.getElementById("x-0");
const xnInput = document.getElementById("x-n");
const yx0Input = document.getElementById("y-x-0");
const hInput = document.getElementById("h");
const epsInput = document.getElementById("eps");

const inputs = [x0Input, xnInput, yx0Input, hInput, epsInput];

x0Input.addEventListener('input', event => {
    if (!isValidInput(x0Input)) {
        x0Input.classList.add("is-danger");
        return;
    } else {
        x0Input.classList.remove("is-danger");
    }

    if (!isValidInput(xnInput)) {
        return;
    }

    validateInterval()

    if (isValidInput(hInput)) {
        if (!isValidStep()) {
            hInput.classList.add("is-danger");
        } else {
            hInput.classList.remove("is-danger");
        }
    }

})

xnInput.addEventListener('input', event => {
    if (!isValidInput(xnInput)) {
        xnInput.classList.add("is-danger");
        return;
    } else {
        xnInput.classList.remove("is-danger");
    }

    if (!isValidInput(x0Input)) {
        return;
    }

    validateInterval()

    if (isValidInput(hInput)) {
        if (!isValidStep()) {
            hInput.classList.add("is-danger");
        } else {
            hInput.classList.remove("is-danger");
        }
    }

})

yx0Input.addEventListener('input', event => {
    if (!isValidInput(yx0Input)) {
        yx0Input.classList.add("is-danger");
    } else {
        yx0Input.classList.remove("is-danger");
    }
})

hInput.addEventListener('input', event => {
    if (!isValidInput(hInput)) {
        hInput.classList.add("is-danger");
        return;
    } else {
        hInput.classList.remove("is-danger");
    }

    if (!isValidStep()) {
        hInput.classList.add("is-danger");
        return;
    } else {
        hInput.classList.remove("is-danger");
    }

})

epsInput.addEventListener('input', event => {
    if (!isValidInput(epsInput)) {
        epsInput.classList.add("is-danger");
        return;
    } else {
        epsInput.classList.remove("is-danger");
    }

    let epsValue = formatValue(epsInput.value);

    if (!isValidEpsilon()) {
        epsInput.classList.add("is-danger");
    } else {
        epsInput.classList.remove("is-danger");
    }
})

function validateInterval() {
    let x0Value = formatValue(x0Input.value);
    let xnValue = formatValue(xnInput.value);

    if (isNaN(x0Value) || isNaN(xnValue)) {
        return false;
    }

    if (x0Value >= xnValue) {
        xnInput.classList.add("is-danger");
        x0Input.classList.add("is-danger");
        return false;
    } else {
        xnInput.classList.remove("is-danger");
        x0Input.classList.remove("is-danger");
        return true;
    }
}

function isValidStep() {
    let hValue = formatValue(hInput.value);
    if (validateInterval()) {
        let x0Value = formatValue(x0Input.value);
        let xnValue = formatValue(xnInput.value);
        let intervalLength = xnValue - x0Value;
        return !(hValue > intervalLength || hValue <= 0);
    }
    return hValue > 0;
}

function isValidEpsilon() {
    return formatValue(epsInput.value) > 0;
}

function isValidInput(input)  {
    let inputValue = formatValue(input.value)
    return !isNaN(inputValue);
}

function formatValue(value) {
    let valueLine = value.replaceAll(" ", "");
    return valueLine !== "" ? Number(valueLine.replaceAll(",", ".")) : NaN;
}
