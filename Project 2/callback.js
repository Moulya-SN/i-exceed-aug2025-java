const fs = require('fs');
const readline = require('readline');
const Big = require('big.js');

// Load currency data from JSON using a callback
function loadCurrencyData(filepath, callback) {
  fs.readFile(filepath, 'utf-8', (err, rawData) => {
    if (err) {
      return callback(`Failed to read currency file: ${err.message}`);
    }

    let parsedData;
    try {
      parsedData = JSON.parse(rawData);
    } catch (parseErr) {
      return callback(`Failed to parse JSON: ${parseErr.message}`);
    }

    const currencyMap = {};
    Object.values(parsedData).forEach(entry => {
      if (entry.code && entry.unicode && typeof entry.decimals === 'number') {
        currencyMap[entry.code.toUpperCase()] = {
          symbol: entry.unicode,
          decimals: entry.decimals
        };
      }
    });

    callback(null, currencyMap);
  });
}

// Convert to smallest unit using Big.js
function convertToSmallestUnit(amountStr, code, currencyMap, callback) {
  const currency = currencyMap[code.toUpperCase()];
  const amount = new Big(amountStr);

  if (!currency) {
    return callback(`Currency code "${code}" not found.`);
  }

  const multiplier = new Big(10).pow(currency.decimals);
  const smallestUnit = amount.times(multiplier);

  callback(null, {
    value: smallestUnit, // removes floating imprecision
    symbol: currency.symbol
  });
}

// Main function to run CLI input using callbacks
function startCurrencyConverter() {
  loadCurrencyData('currency.json', (err, currencyMap) => {
    if (err) {
      console.error(err);
      process.exit(1);
    }

    const rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout
    });

    rl.question('Enter currency code (e.g., USD, INR): ', (code) => {
      rl.question('Enter amount: ', (amountStr) => {
        try {
          convertToSmallestUnit(amountStr, code, currencyMap, (err, result) => {
            if (err) {
              console.error(err);
            } else {
              console.log(`Converted amount: ${result.value} ${result.symbol}`);
            }
            rl.close();
          });
        } catch (e) {
          console.error('Invalid input or calculation error:', e.message);
          rl.close();
        }
      });
    });
  });
}


// Run the tool
startCurrencyConverter();
