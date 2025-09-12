const fs = require("fs");
const readline = require("readline");
const https = require("https");
const Big = require("big.js");
 
const rawData = fs.readFileSync("currency.json", "utf-8");
 
let currencyData;
try {
  currencyData = JSON.parse(rawData);
} catch (err) {
  console.error("Failed to parse JSON:", err.message);
  process.exit(1);
}
 
const currencyMap = {};
Object.values(currencyData).forEach((entry) => {
  if (entry.code && entry.unicode && typeof entry.decimals === "number") {
    currencyMap[entry.code.toUpperCase()] = {
      minorUnitName: entry.unicode,
      decimals: entry.decimals,
    };
  }
});
 
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
 
rl.question("From currency code (e.g., USD): ", (src) => {
  rl.question("To currency code (e.g., INR): ", (dst) => {
    rl.question("Amount (in major units): ", (amtStr) => {
      const from = src.toUpperCase();
      const to = dst.toUpperCase();
      const srcCurr = currencyMap[from];
      const dstCurr = currencyMap[to];
 
      if (!srcCurr || !dstCurr) {
        console.error("Unknown currency code.");
        rl.close();
        return;
      }
      let amount;
      try {
        amount = new Big(amtStr);
      } catch {
        console.error("Invalid number.");
        rl.close();
        return;
      }
 
      // Convert input amount to minor units for display
      const amountInMinorUnits = amount.times(Big(10).pow(srcCurr.decimals));
      console.log(
        `Input amount: ${amount.toString()} ${from} (${amountInMinorUnits.toString()} ${
          srcCurr.minorUnitName
        })`
      );
 
      const key = process.env.EXCHANGERATE_API_KEY;
      if (!key) {
        console.error(
          "API key missing. Please set EXCHANGERATE_API_KEY environment variable."
        );
        rl.close();
        return;
      }
 
      const url = `https://api.forexrateapi.com/v1/convert?api_key=${key}&from=${from}&to=${to}&amount=${amount.toString()}`;
 
      https
        .get(url, (res) => {
          let payload = "";
          res.on("data", (chunk) => {
            payload += chunk;
          });
          res.on("end", () => {
            try {
              const json = JSON.parse(payload);
 
              if (!json.success) {
                console.error("API Error:", json.error || json);
              } else {
                // Inspect the value before creating Big
                console.log("API conversion result raw:", json.result);
                if (
                  typeof json.result === "number" ||
                  (typeof json.result === "string" && !isNaN(json.result))
                ) {
                  const result = new Big(json.result);
                  const formattedResult = result.toFixed(dstCurr.decimals);
                  const resultInMinorUnits = result.times(
                    Big(10).pow(dstCurr.decimals)
                  );
                  console.log(
                    `\nConverted amount: ${formattedResult} ${to} (${resultInMinorUnits.toString()} ${
                      dstCurr.minorUnitName
                    })`
                  );
                  console.log(`Conversion rate: ${json.info.quote}`);
                  //console.log('Full API response:', json);
                } else {
                  console.error(
                    "API returned invalid conversion result:",
                    json.result
                  );
                }
              }
            } catch (e) {
              console.error("Invalid API response:", e.message);
            } finally {
              rl.close();
            }
          });
        })
        .on("error", (err) => {
          console.error("Failed request:", err.message);
          rl.close();
        });
    });
  });
});
//export EXCHANGERATE_API_KEY='1903c962c295b3863499f358099a43d6'
//NODE_TLS_REJECT_UNAUTHORIZED=0 node convert.js