const sde1 = document.getElementById("sde-1-span")
const sde2 = document.getElementById("sde-2-span");
const sde3 = document.getElementById("sde-3-span");

const x0 = document.getElementById("x-0-label");
const xn = document.getElementById("x-n-label");
const yx0 = document.getElementById("y-x-0-label");
const h = document.getElementById("h-label");
const eps = document.getElementById("eps-label");

const conditions = document.getElementById("conditions");

katex.render(
    "y' = y + (1 + x)y^2",
    sde1
);

katex.render(
    "y' = y/x - 2/x^2",
    sde2
);

katex.render(
    "y' = x + y + 4",
    sde3
);

katex.render(
    "X_0 :",
    x0
);

katex.render(
    "X_n :",
    xn
);

katex.render(
    "Y(X_0) :",
    yx0
);

katex.render(
    "h :",
    h
);

katex.render(
    "\\epsilon :",
    eps
);

katex.render(
    "\\begin{array}{l}" +
    "\\bullet \\ X_n > X_0 \\\\" +
    "\\bullet \\ h \\leq X_n - X_0 \\\\" +
    "\\bullet \\ h > 0 \\\\" +
    "\\bullet \\ \\epsilon > 0 \\\\" +
    "\\bullet \\ \\text{REAL NUMBERS}" +
    "\\end{array}",
    conditions
);

